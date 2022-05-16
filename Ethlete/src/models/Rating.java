/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gaming
 */
public class Rating {
    private int id_review;
    private int id;
    private int review;
    private int id_match;

    public Rating(int id_review, int id, int review, int id_match) {
        this.id_review = id_review;
        this.id = id;
        this.review = review;
        this.id_match = id_match;
    }
    public Rating(int id, int review, int id_match) {
        this.id = id;
        this.review = review;
        this.id_match = id_match;
    }

    public int getId_review() {
        return id_review;
    }

    public int getId() {
        return id;
    }

    public int getReview() {
        return review;
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    @Override
    public String toString() {
        return "Rating{" + "id_review=" + id_review + ", id=" + id + ", review=" + review + ", id_match=" + id_match + '}';
    }
            
    
}
