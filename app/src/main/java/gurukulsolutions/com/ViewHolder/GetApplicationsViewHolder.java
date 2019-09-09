package gurukulsolutions.com.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import gurukulsolutions.com.Interface.ItemClickListener;
import gurukulsolutions.com.R;

public class GetApplicationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
  public TextView txtLoanType,txtLoanAmount,txtOccupation,txtNetSalary,txtApplicationNo;
  private ItemClickListener itemClickListener;

    public GetApplicationsViewHolder(@NonNull View itemView)
    {
        super( itemView );
        txtApplicationNo=itemView.findViewById( R.id.user_dashboard_application_no );
        txtLoanType=itemView.findViewById( R.id.user_dashboard_loantype );
        txtLoanAmount=itemView.findViewById( R.id.user_dashboard_loanamount);
        txtOccupation=itemView.findViewById( R.id.user_dashboard_Occupation );
        txtNetSalary=itemView.findViewById( R.id.user_dashboard_netsalary );
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
