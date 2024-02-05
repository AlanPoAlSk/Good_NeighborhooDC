package com.alanskrzecz.goodneighborhoodc.models;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=5)
	private String title;
	@Size(min=10)
	private String description;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	@Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] imageBytes;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "accepted_user_id") // Foreign key for the user who accepted the task
    private User acceptedUser;
	
	public enum TaskStatus {
	    OPEN, ACCEPTED
	}

	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.OPEN;
	
	public Task() {}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public byte[] getImageBytes() {
        return imageBytes;
    }
	
	public String getImageBase64() {
	    if (imageBytes != null) {
	        String base64Data = Base64.getEncoder().encodeToString(imageBytes);
	        return "data:image/png;base64," + base64Data;
	    }
	    return null;
	}
	
	@Transient
    private MultipartFile imageFile;
	
	public MultipartFile getImageFile() {
        return imageFile;
    }
	
	public void setImageFile(MultipartFile imageFile) {
		try {
            this.imageBytes = imageFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public User getAcceptedUser() {
		return acceptedUser;
	}


	public void setAcceptedUser(User acceptedUser) {
		this.acceptedUser = acceptedUser;
	}


	public TaskStatus getStatus() {
		return status;
	}


	public void setStatus(TaskStatus status) {
		this.status = status;
	}


	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    

}
