package com.company;

public class AppController {


    private AdjacencyMatrixGraph _graph;
    private PairwiseDisjointSets _pairwiseDisjointSets;



    public AppController() {
        this.setGrapgh(null); // 객체 생성 시에 그래프 객체는 아직 존재하지 않는다

        this.setPairwiseDisjointSets(null);

    }


    public PairwiseDisjointSets PairwiseDisjointSets() {
        return this._pairwiseDisjointSets;
    }

    public void setPairwiseDisjointSets(PairwiseDisjointSets _pairwiseDisjointSets) {
        this._pairwiseDisjointSets = _pairwiseDisjointSets;
    }

    private AdjacencyMatrixGraph graph() {
        return this._graph;

    }

    private void setGrapgh(AdjacencyMatrixGraph newGraph) {
        this._graph = newGraph;
    }
    private void inputAndMakeGraph() {

        // 여기를 채우시오

        AppView.outputLine("> 입력할 그래프의 vertex 수의 edge 수를 먼저 입력 해야 합니다:");
        int numberOfVertices = this.inputNumberOfVertices();
        this.setGrapgh(new AdjacencyMatrixGraph(numberOfVertices));

        int numberOfEdges = this.inputNumberOfEdges();
        AppView.outputLine("");
        AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다");
        this.initCycleDetection(); // 사이클 검사 객체 생성




        int edgeCount = 0;
        while (edgeCount < numberOfEdges) {

            Edge edge = this.inputEdge();

            if (this.graph().edgeDoesExist(edge)) {

                AppView.outputLine("(오류) 입력된 edge (" + edge.tailVertex() + "." + edge.headVertex() + ") 는 그래프에 이미 존재합니다.");

            } else {

                edgeCount++;
                this.graph().addEdge(edge);
                AppView.outputLine("!새로운 edge (" + edge.tailVertex() + "," + edge.headVertex() + ") 가 그래프에 삽입되었습니다.");
                if(this.addedEdgeDoesMakeCycle(edge)) {

                    AppView.outputLine("![Cycle] 삽입된 edge (" + edge.tailVertex()+", " + edge.headVertex() + ") 는 그래프에 사이클을 만들었습니다.");

                }
            }
        }


    }

    // 해결방법 : 반복자를 구현하여 사용자에게 제공하는 것이 해결책이다


    // 이번주는 edgeDoesExist()를 이용하여 간접적으로 해결 가능하다

    // 존재하는 모든 edge에 대해 검사하여 존재하는 edge들만 출력할 수 있다

    private void showGraph() {

        // 여기를 채우시오

        AppView.outputLine("");
        AppView.outputLine("> 입력된 그래프는 다음과 같습니다.:");
        for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++) {
            AppView.output("[" + tailVertex + "] ->");
            for (int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {

                if (this.graph().edgeDoesExist(new Edge(tailVertex, headVertex))) {

                    AppView.output(" " + headVertex);

                }
            }
            AppView.outputLine("");

        }
    }

    public void run() {

        AppView.outputLine("<<< 입력되는 그래프의 사이클 검사를  시작합니다 >>> ");
        this.inputAndMakeGraph();

        AppView.outputLine("");
        this.showGraph();
        AppView.outputLine("");

        AppView.outputLine("<<< 그래프의 입력과 사이클 검사를 종료합니다 >>>");
    }

    private int inputNumberOfVertices() {

        int numberOfVertices = AppView.inputNumberOfVertices();
        while (numberOfVertices <= 0) {

            AppView.outputLine("[오류] vertex 수는 0 보다 커야 합니다: " + numberOfVertices);
            numberOfVertices = AppView.inputNumberOfVertices();

        }

        return numberOfVertices;

    }

    private int inputNumberOfEdges() {
        int numberOfEdges = AppView.inputNumberOfEdges();

        while (numberOfEdges < 0) {

            AppView.outputLine("[오류] vertex 수는 0 보다 크거나 같아야 합니다 " + numberOfEdges);
            numberOfEdges = AppView.inputNumberOfEdges();


        }
        return numberOfEdges;


    }

    private Edge inputEdge() {

        do {

            AppView.outputLine("-입력할 edge의 두 vertex를 차례로 입력해야 합니다");
            int tailVertex = AppView.inputTailVertex();
            int headVertex = AppView.inputHeadVertex();

            if (this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {

                if (tailVertex == headVertex) {
                    AppView.outputLine("[오류] 두 vertex 번호가 동일합니다");
                    // 순환 금지!
                } else {

                    return (new Edge(tailVertex, headVertex));
                    // 객체 생성해서 각 vertex 연결한다

                }
            } else {

                if (!this.graph().vertexDoesExist(tailVertex)) {

                    AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다 : " + tailVertex);


                }
                if (!this.graph().vertexDoesExist(headVertex)) {

                    AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다 : " + headVertex);


                }
            }
        } while (true); // 이건 뭐지?

    }

    private void initCycleDetection() {
        // vertex수를 알게된 직후에 초기화를 한다


        this.setPairwiseDisjointSets( new PairwiseDisjointSets(this.graph().numberOfVertices()));
    }

    private boolean addedEdgeDoesMakeCycle(Edge anAddedEdge) {

        int tailVertex = anAddedEdge.tailVertex();
        int headVertex = anAddedEdge.headVertex();
        int setForTailVertex = this.PairwiseDisjointSets().find(tailVertex);
        int setForHeadVertex = this.PairwiseDisjointSets().find(headVertex);

        if(setForTailVertex == setForHeadVertex) {

            return true;

        }else {
            // 사이클을 만들지 않는 경우
            this.PairwiseDisjointSets().union(setForTailVertex,setForHeadVertex);
            return false;

        }
    }
}
