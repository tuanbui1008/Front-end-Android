package com.btl.bookingroom.control;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.btl.bookingroom.R;
import com.btl.bookingroom.constants.Constant;
import com.btl.bookingroom.model.dbo.BookRoom;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class AdapterHistoryBookRoom extends RecyclerView.Adapter<AdapterHistoryBookRoom.ViewHolder> {
    private Context context;
    private List<BookRoom> lst;

    public AdapterHistoryBookRoom(Context context, List<BookRoom> lst) {
        this.context = context;
        this.lst = lst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_list_history, parent, false);
        return new AdapterHistoryBookRoom.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
        BookRoom bookRoom = lst.get(position);
        if (Objects.nonNull(bookRoom.getNameHotel())) {
            holder.lblHotel.setText(bookRoom.getNameHotel());
        }
        if (Objects.nonNull(bookRoom.getAddress())) {
            holder.lblAddress.setText(bookRoom.getAddress());
        }
        if (Objects.nonNull(bookRoom.getCategory())) {
            holder.lblCategory.setText(bookRoom.getCategory());
        }
        if (Objects.nonNull(bookRoom.getRoomCode())) {
            holder.lblRoomCode.setText(bookRoom.getRoomCode());
        }
        if (Objects.nonNull(bookRoom.getAmountPerson())) {
            holder.lblAmountPerson.setText(String.valueOf(bookRoom.getAmountPerson()));
        }
        if (Objects.nonNull(bookRoom.getAmountRoom())) {
            holder.lblAmountRoom.setText(String.valueOf(bookRoom.getAmountRoom()));
        }
        if (Objects.nonNull(bookRoom.getDateOfArrival())) {
            holder.lblDateOfArrival.setText(format.format(bookRoom.getDateOfArrival()));
        }
        if (Objects.nonNull(bookRoom.getDateGo())) {
            holder.lblDateGo.setText(format.format(bookRoom.getDateGo()));
        }
        if (Objects.nonNull(bookRoom.getCreatedDate())) {
            holder.lblCreatedDate.setText(format.format(bookRoom.getCreatedDate()));
        }
        if (Objects.nonNull(bookRoom.getPrice())) {
            holder.lblPrice.setText(String.valueOf(bookRoom.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lblHotel;
        private TextView lblAddress;
        private TextView lblCategory;
        private TextView lblRoomCode;
        private TextView lblAmountPerson;
        private TextView lblAmountRoom;
        private TextView lblDateOfArrival;
        private TextView lblDateGo;
        private TextView lblPrice;
        private TextView lblCreatedDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHotel = itemView.findViewById(R.id.lblHotel);
            lblAddress = itemView.findViewById(R.id.lblAddress);
            lblCategory = itemView.findViewById(R.id.lblCategory);
            lblRoomCode = itemView.findViewById(R.id.lblRoomCode);
            lblAmountPerson = itemView.findViewById(R.id.lblAmountPerson);
            lblAmountRoom = itemView.findViewById(R.id.lblAmountRoom);
            lblDateOfArrival = itemView.findViewById(R.id.lblDateOfArrival);
            lblDateGo = itemView.findViewById(R.id.lblDateGo);
            lblPrice = itemView.findViewById(R.id.lblPrice);
            lblCreatedDate = itemView.findViewById(R.id.lblCreatedDate);
//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
