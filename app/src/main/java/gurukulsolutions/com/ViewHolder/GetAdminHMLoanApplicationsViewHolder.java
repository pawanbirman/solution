package gurukulsolutions.com.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gurukulsolutions.com.Interface.ItemClickListener;
import gurukulsolutions.com.R;

public class GetAdminHMLoanApplicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView txtAdminName,txtAdminContactNo;
    public TextView txtAdminOccupation;
    public TextView txtAdminOldLoan;
    public TextView txtAdminNewLoanAmount,txtAdminMeetingTime,txtAdminAddress,txtAdminLoanType,txtAdminApplicationNo;
    public ImageView imageAdminhmAadhaarFront,imageAdminhmAadhaarBack,imageAdminhmPhoto,imageAdminhmPanCard;

    private ItemClickListener itemClickListener;

    public GetAdminHMLoanApplicationsViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txtAdminName=itemView.findViewById( R.id.admin_dashboard_hmloanborrowerName );
        txtAdminContactNo=itemView.findViewById( R.id.admin_dashboard_hmloancontactNo );
        txtAdminOccupation=itemView.findViewById( R.id.admin_dashboard_hmloanOccupation );
        txtAdminOldLoan=itemView.findViewById( R.id.admin_dashboard_hmloanoldLoan);
        txtAdminNewLoanAmount=itemView.findViewById( R.id.admin_dashboard_hmloannewLoanAmount );
        txtAdminMeetingTime=itemView.findViewById( R.id.admin_dashboard_hmloanmeetingTime );
        txtAdminAddress=itemView.findViewById( R.id.admin_dashboard_hmloanaddress );
        txtAdminLoanType=itemView.findViewById( R.id.admin_dashboard_hmloanType );
        txtAdminApplicationNo=itemView.findViewById( R.id.admin_dashboard_hmloanapplication_no );

        imageAdminhmAadhaarFront=itemView.findViewById( R.id.admin_dashboard_hmloanaadhaarFront);
        imageAdminhmAadhaarBack=itemView.findViewById( R.id.admin_dashboard_hmloanaadhaarBack);
        imageAdminhmPhoto=itemView.findViewById( R.id.admin_dashboard_hmloanphoto );
        imageAdminhmPanCard=itemView.findViewById( R.id.admin_dashboard_hmloanpancard );

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
