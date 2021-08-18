package sg.edu.rp.c346.id20022735.movies;

import java.io.Serializable;

public class Movies implements Serializable {

	private int id;
	private String name;
	private String description;
	private int stars;

    public Movies(String name, String description, int stars) {
        this.name = name;
        this.description = description;
        this.stars = stars;
    }

    public Movies(int id, String name, String description, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Movies setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Movies setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movies setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Movies setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";

        //or
        for(int i = 0; i < stars; i++){
            starsString += "* ";
        }
        return starsString;

    }
}
