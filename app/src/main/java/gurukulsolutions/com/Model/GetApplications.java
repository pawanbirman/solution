package gurukulsolutions.com.Model;

public class GetApplications {
    private String Borrower_Name, PhoneNo, DOB, Company_Name, DOJ, Net_Salary, Old_Loan_Amount, New_Loan_Amount;
    private String Address, Meeting_Time, Gender, Occupation, Old_Loan_Exists_or_not, Loan_Type;
    private String Tax_Payer_Name, PhoneNumber, Email, AADHAAR_number, PAN_Card, Application_type;
    private String Owner_Name, Contact_No, DOExpiry_of_current_policy, Vehicle_Type, Insurance_Type, RC_Front, RC_Back, pid;


    public GetApplications() {
    }

    public GetApplications(String borrower_Name, String phoneNo, String DOB, String company_Name, String DOJ, String net_Salary, String old_Loan_Amount, String new_Loan_Amount, String address, String meeting_Time, String gender, String occupation, String old_Loan_Exists_or_not, String loan_Type, String aadhar_Front, String aadhar_Back, String photo, String pan_Card, String tax_Payer_Name, String phoneNumber, String email, String AADHAAR_number, String PAN_Card, String application_type, String owner_Name, String contact_No, String DOExpiry_of_current_policy, String vehicle_Type, String insurance_Type, String RC_Front, String RC_Back, String pid) {
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
        Tax_Payer_Name = tax_Payer_Name;
        PhoneNumber = phoneNumber;
        Email = email;
        this.AADHAAR_number = AADHAAR_number;
        this.PAN_Card = PAN_Card;
        Application_type = application_type;
        Owner_Name = owner_Name;
        Contact_No = contact_No;
        this.DOExpiry_of_current_policy = DOExpiry_of_current_policy;
        Vehicle_Type = vehicle_Type;
        Insurance_Type = insurance_Type;
        this.RC_Front = RC_Front;
        this.RC_Back = RC_Back;
        this.pid = pid;
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

    public String getTax_Payer_Name() {
        return Tax_Payer_Name;
    }

    public void setTax_Payer_Name(String tax_Payer_Name) {
        Tax_Payer_Name = tax_Payer_Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAADHAAR_number() {
        return AADHAAR_number;
    }

    public void setAADHAAR_number(String AADHAAR_number) {
        this.AADHAAR_number = AADHAAR_number;
    }

    public String getPAN_Card() {
        return PAN_Card;
    }

    public void setPAN_Card(String PAN_Card) {
        this.PAN_Card = PAN_Card;
    }

    public String getApplication_type() {
        return Application_type;
    }

    public void setApplication_type(String application_type) {
        Application_type = application_type;
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