package bot.models;

public class Complaint {
    private Long complaintId;
    private String text;
    private boolean isConsidered;
    private Long userId;
    private Long adminId;

    public Complaint(Long id, String text, boolean considered, Long userId, Long adminId)
    {
        complaintId = id;
        this.text = text;
        isConsidered = considered;
        this.userId = userId;
        this.adminId = adminId;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public String getText() {
        return text;
    }

    public boolean isConsidered() {
        return isConsidered;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setConsidered(boolean considered) {
        isConsidered = considered;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
