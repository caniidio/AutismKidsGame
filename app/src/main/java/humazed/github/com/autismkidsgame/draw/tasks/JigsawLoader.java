/*
 * Copyright (c) 2015. Jay Paulynice (jay.paulynice@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package humazed.github.com.autismkidsgame.draw.tasks;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.GridView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import humazed.github.com.autismkidsgame.draw.adapter.JigsawGridAdapter;
import humazed.github.com.autismkidsgame.draw.dao.ImageDao;
import humazed.github.com.autismkidsgame.draw.dao.impl.ImageDaoImpl;
import humazed.github.com.autismkidsgame.draw.model.ImageEntity;


public class JigsawLoader extends AsyncTask<Long, Integer, List<Bitmap>> {
    /** Image dao */
    private ImageDao dao;

    /** The grid view */
    private GridView gridView;

    /** The application context */
    private Context context;

    public JigsawLoader(Context context, GridView gridView) {
        this.context = context;
        this.gridView = gridView;
        this.dao = new ImageDaoImpl(context);
    }

    @Override
    protected List<Bitmap> doInBackground(Long... params) {
        List<ImageEntity> entities = dao.findTiles(params[0]);
        Collections.shuffle(entities);

        return entities.stream().map(ImageEntity::getImage).collect(Collectors.toList());
    }

    @Override
    protected void onPostExecute(List<Bitmap> tiles) {
        int pieces = (int) Math.sqrt(tiles.size());
        JigsawGridAdapter adapter = new JigsawGridAdapter(context, tiles, pieces);

        gridView.setAdapter(adapter);
        gridView.setNumColumns(pieces);
    }
}