package humazed.github.com.autismkidsgame.puzzle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import humazed.github.com.autismkidsgame.R;
import humazed.github.com.autismkidsgame.puzzle.ui.ChoosePicActivity;
import humazed.github.com.autismkidsgame.puzzle.utils.BitmapUtils;

/**
 * puzzle
 * Created by ZQ on 2016/4/24.
 */
public class ChoosePicGridViewAdapter extends ArrayAdapter<Integer> {

    int resourceId;

    public ChoosePicGridViewAdapter(Context context, int resourceId){
        super(context, resourceId);
        this.resourceId = resourceId;
    }

    @Override
    public int getCount(){
        return ChoosePicActivity.icons.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageItem);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResources(
                getContext().getResources(),
                ChoosePicActivity.icons[position],
                300,
                300
        ));

        return view;
    }

    class ViewHolder{
        ImageView imageView;
    }
}
