package gurukulsolutions.com.Model;

public class Products
{
    private  String pname,image,pid,description,Service;

    public Products() {
    }

    public Products(String pname, String image, String pid, String description, String service) {
        this.pname = pname;
        this.image = image;
        this.pid = pid;
        this.description = description;
        Service = service;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }
}