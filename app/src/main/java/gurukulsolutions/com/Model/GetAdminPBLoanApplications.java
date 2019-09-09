package gurukulsolutions.com.Model;

public class GetAdminPBLoanApplications {

    private String Borrower_Name, PhoneNo, DOB, Company_Name, DOJ, Net_Salary, Old_Loan_Amount, New_Loan_Amount;
    private String Address, Meeting_Time, Gender, Occupation, Old_Loan_Exists_or_not, Loan_Type,pid;
    private String Photo,Pan_Card,Aadhar_Front,Aadhar_Back;


    public GetAdminPBLoanApplications() {
    }

    public GetAdminPBLoanApplications(String borrower_Name, String phoneNo, String DOB, String company_Name, String DOJ, String net_Salary, String old_Loan_Amount, String new_Loan_Amount, String address, String meeting_Time, String gender, String occupation, String old_Loan_Exists_or_not, String loan_Type, String pid, String photo, String pan_Card, String aadhar_Front, String aadhar_Back) {
        Borrower_Name = borrower_Name;
        PhoneNo = phoneNo;
        this.DOB = DOB;
        Company_Name = company_Name;
        this.DOJ = DOJ;
        Net_Salary = net_Salary;
        Old_Loan_Amount = old_Loan_Amount;
        New_Loan_Amount = new_Loan_Amount;
        Address = address;
        Meeting_Time = meeting_Time;
        Gender = gender;
        Occupation = occupation;
        Old_Loan_Exists_or_not = old_Loan_Exists_or_not;
        Loan_Type = loan_Type;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getNet_Salary() {
        return Net_Salary;
    }

    public void setNet_Salary(String net_Salary) {
        Net_Salary = net_Salary;
    }

    public String getOld_Loan_Amount() {
        return Old_Loan_Amount;
    }

    public void setOld_Loan_Amount(String old_Loan_Amount) {
        Old_Loan_Amount = old_Loan_Amount;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getOld_Loan_Exists_or_not() {
        return Old_Loan_Exists_or_not;
    }

    public void setOld_Loan_Exists_or_not(String old_Loan_Exists_or_not) {
        Old_Loan_Exists_or_not = old_Loan_Exists_or_not;
    }

    public String getLoan_Type() {
        return Loan_Type;
    }

    public void setLoan_Type(String loan_Type) {
        Loan_Type = loan_Type;
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
