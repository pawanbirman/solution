package gurukulsolutions.com.Model;

public class GetAdminHMLoanApplications {
    private String Borrower_Name,PhoneNo,New_Loan_Amount,Address,Meeting_Time,Occupation;
    private String Loan_Type,Old_Loan_Exists_or_not,pid;
    private String Photo,Pan_Card,Aadhar_Front,Aadhar_Back;

    public GetAdminHMLoanApplications() {
    }

    public GetAdminHMLoanApplications(String borrower_Name, String phoneNo, String new_Loan_Amount, String address, String meeting_Time, String occupation, String loan_Type, String old_Loan_Exists_or_not, String pid, String photo, String pan_Card, String aadhar_Front, String aadhar_Back) {
        Borrower_Name = borrower_Name;
        PhoneNo = phoneNo;
        New_Loan_Amount = new_Loan_Amount;
        Address = address;
        Meeting_Time = meeting_Time;
        Occupation = occupation;
        Loan_Type = loan_Type;
        Old_Loan_Exists_or_not = old_Loan_Exists_or_not;
        this.pid = pid;
        Photo = photo;
        Pan_Card = pan_Card;
        Aadhar_Front = aadhar_Front;
        Aadhar_Back = aadhar_Back;
    }

    public String getBorrower_Name() {
        return Borrower_Name;
    }

    public void setBorrower_Name(String borrower_Name) {
        Borrower_Name = borrower_Name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getNew_Loan_Amount() {
        return New_Loan_Amount;
    }

    public void setNew_Loan_Amount(String new_Loan_Amount) {
        New_Loan_Amount = new_Loan_Amount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMeeting_Time() {
        return Meeting_Time;
    }

    public void setMeeting_Time(String meeting_Time) {
        Meeting_Time = meeting_Time;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getLoan_Type() {
        return Loan_Type;
    }

    public void setLoan_Type(String loan_Type) {
        Loan_Type = loan_Type;
    }

    public String getOld_Loan_Exists_or_not() {
        return Old_Loan_Exists_or_not;
    }

    public void setOld_Loan_Exists_or_not(String old_Loan_Exists_or_not) {
        Old_Loan_Exists_or_not = old_Loan_Exists_or_not;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getPan_Card() {
        return Pan_Card;
    }

    public void setPan_Card(String pan_Card) {
        Pan_Card = pan_Card;
    }

    public String getAadhar_Front() {
        return Aadhar_Front;
    }

    public void setAadhar_Front(String aadhar_Front) {
        Aadhar_Front = aadhar_Front;
    }

    public String getAadhar_Back() {
        return Aadhar_Back;
    }

    public void setAadhar_Back(String aadhar_Back) {
        Aadhar_Back = aadhar_Back;
    }
}
