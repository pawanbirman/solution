package gurukulsolutions.com.Model;

public class GetAdminInsuranceApplications {

    private String Owner_Name,Contact_No,DOExpiry_of_current_policy ;
    private String Vehicle_Type,Insurance_Type,Loan_Type;
    private String RC_Front,RC_Back,pid;

    public GetAdminInsuranceApplications() {
    }

    public GetAdminInsuranceApplications(String owner_Name, String contact_No, String DOExpiry_of_current_policy, String vehicle_Type, String insurance_Type, String loan_Type, String RC_Front, String RC_Back, String pid) {
        Owner_Name = owner_Name;
        Contact_No = contact_No;
        this.DOExpiry_of_current_policy = DOExpiry_of_current_policy;
        Vehicle_Type = vehicle_Type;
        Insurance_Type = insurance_Type;
        Loan_Type = loan_Type;
        this.RC_Front = RC_Front;
        this.RC_Back = RC_Back;
        this.pid = pid;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public String getDOExpiry_of_current_policy() {
        return DOExpiry_of_current_policy;
    }

    public void setDOExpiry_of_current_policy(String DOExpiry_of_current_policy) {
        this.DOExpiry_of_current_policy = DOExpiry_of_current_policy;
    }

    public String getVehicle_Type() {
        return Vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        Vehicle_Type = vehicle_Type;
    }

    public String getInsurance_Type() {
        return Insurance_Type;
    }

    public void setInsurance_Type(String insurance_Type) {
        Insurance_Type = insurance_Type;
    }

    public String getLoan_Type() {
        return Loan_Type;
    }

    public void setLoan_Type(String loan_Type) {
        Loan_Type = loan_Type;
    }

    public String getRC_Front() {
        return RC_Front;
    }

    public void setRC_Front(String RC_Front) {
        this.RC_Front = RC_Front;
    }

    public String getRC_Back() {
        return RC_Back;
    }

    public void setRC_Back(String RC_Back) {
        this.RC_Back = RC_Back;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
