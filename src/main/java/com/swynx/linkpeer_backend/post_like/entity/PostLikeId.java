package com.swynx.linkpeer_backend.post_like.entity;

import java.io.Serializable;
import java.util.Objects;

/*
 * This class represents the PRIMARY KEY of the PostLike entity.
 *
 * Our post_likes table does not have a separate single "id" column.
 *
 * Instead, a like is uniquely identified by:
 *
 * postId + userId
 *
 * Example:
 *
 * postId = 10
 * userId = "user123"
 *
 * Together, they identify one unique like:
 *
 * User "user123" liked Post 10.
 */
public class PostLikeId implements Serializable {

    /*
     * First part of the composite primary key.
     *
     * Represents:
     * post_id in the database.
     */
    private Long postId;

    /*
     * Second part of the composite primary key.
     *
     * Represents:
     * user_id in the database.
     */
    private String userId;


    /*
     * JPA requires an empty constructor.
     *
     * JPA uses it internally when creating this object
     * from database data.
     */
    public PostLikeId() {
    }


    /*
     * This constructor lets us manually create an ID.
     *
     * Example:
     *
     * new PostLikeId(10L, "user123");
     *
     * Meaning:
     *
     * User "user123" + Post 10
     */
    public PostLikeId(Long postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }


    /*
     * equals() checks whether two PostLikeId objects
     * logically represent the same ID.
     *
     * Example:
     *
     * id1 = new PostLikeId(10L, "user123");
     * id2 = new PostLikeId(10L, "user123");
     *
     * These are two different objects in memory,
     * but they contain the same values.
     *
     * So:
     *
     * id1 == id2
     *
     * is false because they are different objects.
     *
     * But:
     *
     * id1.equals(id2)
     *
     * should return true because both represent:
     *
     * Post 10 + User user123
     */
    @Override
    public boolean equals(Object o) {

        // If both references point to the exact same object
        if (this == o) {
            return true;
        }

        // If the other object is null or not a PostLikeId,
        // they cannot represent the same ID
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Convert the Object into PostLikeId
        PostLikeId that = (PostLikeId) o;

        /*
         * Both parts of the composite key must match.
         *
         * Same postId
         *      AND
         * Same userId
         *
         * Only then are the two IDs considered equal.
         */
        return Objects.equals(postId, that.postId)
                && Objects.equals(userId, that.userId);
    }


    /*
     * hashCode() generates a number based on the object's values.
     *
     * Java uses this number to quickly decide where
     * an object might be located, especially in collections
     * like HashSet and HashMap.
     *
     * Think of it like:
     *
     * hashCode()
     *     ↓
     * Quickly find the possible area
     *     ↓
     * equals()
     *     ↓
     * Check whether the objects are actually the same
     *
     * Important rule:
     *
     * If:
     *
     * id1.equals(id2) == true
     *
     * Then:
     *
     * id1.hashCode() == id2.hashCode()
     *
     * should also be true.
     *
     * Since our equals() compares postId and userId,
     * our hashCode() must also use postId and userId.
     */
    @Override
    public int hashCode() {

        // Generate a hash using both parts of the composite key
        return Objects.hash(postId, userId);
    }
}