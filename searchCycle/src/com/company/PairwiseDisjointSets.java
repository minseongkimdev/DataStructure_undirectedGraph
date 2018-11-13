package com.company;

import javafx.util.Pair;

public class PairwiseDisjointSets {
    //Constants
    private static final int INITIAL_SINGLETON_SET_SIZE =1; // 상수, 1개의 원소로 이루어진 집합

    private int _numberOfElements; // 원소의 갯수
    private int [] _parentTree; // 그래프의 상태를 배열로 나타낸다


    public PairwiseDisjointSets(int givenNumberOfElements) { // 원소의 갯수를 변수로 받는다


        //주어진 원소 수 만큼의 singleton set을 만든다 (원소가 하나인 집합)

        this.setNumberOfElements(givenNumberOfElements);
        this.setParentTree(new int [this.numberOfElements()]); // 배열로 생성한다
        for (int rootOfSingletonSet = 0; rootOfSingletonSet <this.numberOfElements() ; rootOfSingletonSet++) {

            this.setSizeOfSetFor(rootOfSingletonSet, INITIAL_SINGLETON_SET_SIZE);
        }
    }





    // union의 원소는 자신의 부모의 원소를 가리키고 루트는 부모가 없어서 -1을 저장!


    public int numberOfElements() { // 원소의 갯수를 티런해주는 게터
        return this._numberOfElements;
    }

    public void setNumberOfElements(int newnumberOfElements) { // 원소의 갯수를 설정하는 세터
        this._numberOfElements = newnumberOfElements;
    }

    public int[] parentTree() { // 그래프의 상태를 나타내는 배열을 리턴해주는 게터
        return this._parentTree;
    }

    public void setParentTree(int[] newParentTree) { // 그래프의 상태를 나타내는 배열에 접근하게 해주는 세터
        this._parentTree = newParentTree;
    }

    private int parentOf(int aMember) { //
        // 부모의 값을 리턴받는다 게터를 이용하라

        // 내부적으로도
        return this.parentTree()[aMember];

    }

    private void setParentOf(int aChildMember, int newParentMember) {
        this.parentTree()[aChildMember] = newParentMember;
    // 여기서 에러 발생한다


        // 내부적으로도 게터를 이용하라
        //
    }

    private boolean parentDoesExist(int aMember) {


        // 버전 1  직접 부모트리에 접근
        // 버전 2  게터에 접근
        // 버전 3  게터를 이용한 parentOf함수를 이용한다

        // 만든 함수를 이용하라


        return(this.parentOf(aMember) >=0);
    }

    private int sizeOfSetFor(int aRoot) {

        return(- this.parentOf(aRoot) ) ;

    }
    // 한 집합의 루트의 parent는 그 집합의 크기의 음수이다
    private void setSizeOfSetFor(int aRoot, int newSize) {

        this.setParentOf(aRoot, -newSize);

    }
    // 일단 컴포넌트의 트리를 찾는다
    // 그리고 나서 collapsing rule을 적용하여
    public int find(int aMember) {
    // candidate == 후보자

        // 일단 후보에 담는다, 그리고 나서 while문을 돌린다


        int rootCandidate = aMember;
        while (this.parentDoesExist(rootCandidate)) {

            rootCandidate = this.parentOf(rootCandidate);
        }

        // while 문을 빠져 나오면

        int root  = rootCandidate;

        // 최종후보를 root에 담는다


        // 트리의 높이를 최적화 시켜서 작동 시간을 줄이기 위함이다

        //레벨이 바뀌고 나서 다시 조정한다

        // weight rule

         // 두 개의 트리를 합칠 때 누가 누구의 부모를 할지 레벨이 작은애를 편입시키는게 합리적이다


        int child = aMember;
        int parent = this.parentOf(child);


        if(parent >= 0 ) {
            while(parent != root) {
                this.setParentOf(child, root);
                child = parent;
                parent = parentOf(child);
            }


        }
        return root;
    }
    public void union(int aMemberA, int aMemberB) {
        // 두개의 원소가 속한 각각의 집합을 합하여 하나의 집합으로 만든다

        // 두 원소가 원래 같은 집합이면 변화가 없다


    int rootOfSetA = find(aMemberA);

    int rootOfSetB = find(aMemberB);

    int sizeOfSetA = this.sizeOfSetFor(rootOfSetA);
    int sizeOfSetB = this.sizeOfSetFor(rootOfSetB);

    if(sizeOfSetA < sizeOfSetB) {

        this.setParentOf(rootOfSetA,rootOfSetB);
        this.setSizeOfSetFor(rootOfSetB,(sizeOfSetA+sizeOfSetB));


    }else{

        this.setParentOf( rootOfSetB, rootOfSetA);
        this.setSizeOfSetFor(rootOfSetA, (sizeOfSetA+sizeOfSetB)); // 여기서 왜 오류가 생겼지?>

    }

    }
}
