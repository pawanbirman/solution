package gurukulsolutions.com.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gurukulsolutions.com.Interface.ItemClickListener;
import gurukulsolutions.com.R;

public class GetAdminPBLoanApplicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtAdminName,txtAdminContactNo,txtAdminDOB;
    public TextView txtAdminGender,txtAdminOccupation,txtAdminCompanyName;
    public TextView txtAdminDOJ,txtAdminNetSalary,txtAdminOldLoan,txtAdminOldLoanAmount;
    public TextView txtAdminNewLoanAmount,txtAdminMeetingTime,txtAdminAddress,txtAdminLoanType,txtAdminApplicationNo;
    public ImageView imageAdminAadhaarFront,imageAdminAadhaarBack,imageAdminPhoto,imageAdminPanCard;

    private ItemClickListener itemClickListener;

    public GetAdminPBLoanApplicationsViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txtAdminName=itemView.findViewById( R.id.admin_dashboard_loanborrowerName );
        txtAdminContactNo=itemView.findViewById( R.id.admin_dashboard_loancontactNo );
        txtAdminDOB=itemView.findViewById( R.id.admin_dashboard_loandob);
        txtAdminGender=itemView.findViewById( R.id.admin_dashboard_loangender );
        txtAdminOccupation=itemView.findViewById( R.id.admin_dashboard_loanOccupation );
        txtAdminCompanyName=itemView.findViewById( R.id.admin_dashboard_loancompanyName);
        txtAdminDOJ=itemView.findViewById( R.id.admin_dashboard_loanDOJ);
        txtAdminNetSalary=itemView.findViewById( R.id.admin_dashboard_loannetSalary );
        txtAdminOldLoan=itemView.findViewById( R.id.admin_dashboard_loanoldLoan);
        txtAdminOldLoanAmount=itemView.findViewById( R.id.admin_dashboard_loanoldLoanAmount );
        txtAdminNewLoanAmount=itemView.findViewById( R.id.admin_dashboard_loannewLoanAmount );
        txtAdminMeetingTime=itemView.findViewById( R.id.admin_dashboard_loanmeetingTime );
        txtAdminAddress=itemView.findViewById( R.id.admin_dashboard_loanaddress );
        txtAdminLoanType=itemView.findViewById( R.id.admin_dashboard_loanType );
        txtAdminApplicationNo=itemView.findViewById( R.id.admin_dashboard_loanapplication_no );

        imageAdminAadhaarFront=itemView.findViewById( R.id.admin_dashboard_loanaadhaarFront);
        imageAdminAadhaarBack=itemView.findViewById( R.id.admin_dashboard_loanaadhaarBack);
        imageAdminPhoto=itemView.findViewById( R.id.admin_dashboard_loanphoto );
        imageAdminPanCard=itemView.findViewById( R.id.admin_dashboard_loanpancard );

    }


    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick( v,getAdapterPosition(),false );
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
