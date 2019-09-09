package gurukulsolutions.com.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gurukulsolutions.com.Interface.ItemClickListener;
import gurukulsolutions.com.R;

public class GetAdminITRApplicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtAdminName,txtAdminContactNo;
    public TextView txtAdminApplicationNo;
    public TextView txtAdminEmail,txtAdminAadhaarNo,txtAdminPanNo,txtAdminApplicationType;


    private ItemClickListener itemClickListener;

    public GetAdminITRApplicationsViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txtAdminName=itemView.findViewById( R.id.admin_dashboard_itrTaxPayerName );
        txtAdminContactNo=itemView.findViewById( R.id.admin_dashboard_itrcontactNo );
        txtAdminEmail=itemView.findViewById( R.id.admin_dashboard_itrEmail );
        txtAdminAadhaarNo=itemView.findViewById( R.id.admin_dashboard_itraadhaarno);
        txtAdminPanNo=itemView.findViewById( R.id.admin_dashboard_itrpanno );
        txtAdminApplicationNo=itemView.findViewById( R.id.admin_dashboard_itrapplication_no );
        txtAdminApplicationType=itemView.findViewById( R.id.admin_dashboard_itrapplicationType );


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
