package jboard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ArticleComment implements Serializable {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	private Article article;
	
	private int importance;
	
	private String comment;

	public ArticleComment() {}
	public ArticleComment(Article parentArticle) {
		this.article = parentArticle;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
}
