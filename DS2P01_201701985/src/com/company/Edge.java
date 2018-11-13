package com.company;

public class Edge {

    private int _tailVertex; // 시작점 왼쪽
    private int _headVertex; // 끝나는점 오른쪽



    public Edge(int givenTailVertex, int givenHeadVertex) {
        this._tailVertex = givenTailVertex;
        this._headVertex = givenHeadVertex;



    }

    public void setTailVertex(int newTailVertex) {
        //setter
        this._tailVertex = newTailVertex;


    }

    public int tailVertex() {
        //getter
        return this._tailVertex;


    }

    public void setGeadVertex(int newHeadVertex) {
        //setter
        this._headVertex = newHeadVertex;



    }

    public int headVertex() {
        //getter
        return this._headVertex;


    }


}
