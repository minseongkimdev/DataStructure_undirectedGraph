package com.company;

public class AdjacencyMatrixGraph {


    //private constants

    private static final int EDGE_EXIST = 1; // 프로그램의 가독성을 높이기 위해 이렇게 문자로 선언한다

    private static final int EDGE_NONE = 0;

    //private instance vatiables

    private int _numberOfVertices; // 꼭짓점의 갯수
    private int _numberOfEdges; // 경로의 갯수
    private int[][] _adjacency; // 그래프 행렬

    //생성자 constructor
    public AdjacencyMatrixGraph (int givenNumberOfVertices) {

        this.setnumberOfVertices(givenNumberOfVertices);// 주어진 꼭짓점 갯수
        this.setNumberOfEdges(0);// 경로의 수는 0개로 초기화
        this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
        // 이차원 배열 객체 생성, 꼭지점의 갯수만큼 가로 세로 생성
        for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {

            for (int headVertex =0; headVertex < this.numberOfVertices(); headVertex++) {

                this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE;


            }
        }
        // 이중for문으로 이차원 배열의 각 원소를 0 으로 초기화 하는 과정



    }


    public int numberOfVertices() {
        return this._numberOfVertices;

    }

    public int numberOfEdges() {
        return this._numberOfEdges;

    }
    // for checking statux

    //그 경로가 이어져 있는지 확인하는 메소드
    private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {

        return this.adjacency()[tailVertex][headVertex] != AdjacencyMatrixGraph.EDGE_NONE;


    }
    //그 꼭짓점이 존재하는지 확인하는 메소드
    //꼭짓점은 0보다 크고 꼭짓점의 갯수보다 작아야 한다
    //인덱싱이라고 생각하면 편하다


    public boolean vertexDoesExist(int aVertex) {

        return (aVertex >=0 && aVertex <this.numberOfVertices());


    }
    // 그 경로 존재하는지 확인하기 위한 메소드

    public boolean edgeDoesExist (Edge anEdge) {
        if(anEdge != null) {

            int tailVertex = anEdge.tailVertex();
            int headVertex = anEdge.headVertex();
            if(this.vertexDoesExist(tailVertex)&&this.vertexDoesExist(headVertex)) {

                return (this.adjacencyOfEdgeDoesExist(tailVertex,headVertex));


            }
        }

        return false;


    }

    // 주어진 edge 의 vertex 가 유효한지, anEdge가 이미 존재하는지 nondirected 라서 반대방향도 같이 삽입해야한다!



    public boolean addEdge(Edge anEdge) {
        if(anEdge!=null) {

            int tailVertex = anEdge.tailVertex();
            int headVertex = anEdge.headVertex();

            if (this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {

                this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
                this.setNumberOfEdges(this.numberOfEdges()+1);
                return true;

            }
        }
        return false;


    }
    private void setnumberOfVertices (int numberOfVertices){
        this._numberOfVertices = numberOfVertices;


    }

    private void setNumberOfEdges(int numberOfEdges){
        this._numberOfEdges = numberOfEdges;

    }
    private int [][] adjacency() {
        return this._adjacency;

    }
    private void setAdjacency(int [][] newAdjacency) {
        this._adjacency = newAdjacency;
    }
}
