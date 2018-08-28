package com.master.care.ui.main;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.master.care.R;
import com.master.care.model.DoctorsDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.NewsViewHolder> {

    public interface Callbacks {
        public void onDataClick(@NonNull DoctorsDataModel.DataModel dataModel);
    }

    private Callbacks mCallbacks;
    private List<DoctorsDataModel.DataModel> dataModelList;

    public HomeDataAdapter(List<DoctorsDataModel.DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_row, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        final DoctorsDataModel.DataModel dataModel = dataModelList.get(position);

        holder.doctorName.setText(String.valueOf(dataModel.getFirstName() + " " + dataModel.getLastName()));

        if (dataModelList.get(position).getQualifications() != null) {
            StringBuilder degree = new StringBuilder();
            StringBuilder speciality = new StringBuilder();
            for (DoctorsDataModel.Qualification qualification : dataModelList.get(position).getQualifications()) {

                if (degree.toString().length() > 0) {
                    degree.append(",");
                    degree.append(qualification.getDegree());
                } else {
                    degree.append(qualification.getDegree());
                }

                if (speciality.toString().length() > 0) {
                    speciality.append(",");
                    speciality.append(qualification.getSpecialty());
                } else {
                    speciality.append(qualification.getSpecialty());
                }

            }
            holder.doctorEducation.setText(degree.toString());
            holder.doctorSpeciality.setText(speciality.toString());
        }

        if (dataModel.getRating()!=null && dataModel.getRating().length()>0){

            holder.ratingBar.setRating(Float.parseFloat(dataModel.getRating()));
        }

        if (dataModel.getLinks() != null && dataModel.getLinks().size() >= 12) {
            String picUrl = dataModel.getLinks().get(11).getHref();
            if (!picUrl.contains("jpg")) {
                picUrl = String.valueOf(picUrl + ".jpg");
            }
            Uri uri = Uri.parse(picUrl);
            holder.profileImage.setController(
                    Fresco.newDraweeControllerBuilder()
                            .setTapToRetryEnabled(true)
                            .setUri(uri)
                            .build());
            holder.profileImage.getHierarchy().setPlaceholderImage(R.drawable.profile_placeholder);
            holder.profileImage.getHierarchy().setFailureImage(R.drawable.profile_placeholder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallbacks != null)
                    mCallbacks.onDataClick(dataModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (dataModelList != null ? dataModelList.size() : 0);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        AppCompatTextView doctorName;

        @BindView(R.id.education)
        AppCompatTextView doctorEducation;

        @BindView(R.id.esp)
        AppCompatTextView doctorSpeciality;

        @BindView(R.id.profileImage)
        SimpleDraweeView profileImage;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
