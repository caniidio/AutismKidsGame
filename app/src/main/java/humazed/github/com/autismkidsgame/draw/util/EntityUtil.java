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

package humazed.github.com.autismkidsgame.draw.util;


import android.content.ContentValues;

import humazed.github.com.autismkidsgame.draw.model.ImageEntity;

import static humazed.github.com.autismkidsgame.draw.util.Base64Util.bitMapToBase64;
import static humazed.github.com.autismkidsgame.draw.util.DBUtil.*;

public final class EntityUtil {

    private EntityUtil() {}

    /**
     * Convert the given image entity to content values
     *
     * @param entity the entity to convert
     * @return content values object
     */
    public static ContentValues entityToContentValues(ImageEntity entity) {
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN, entity.getName());
        cv.put(IMAGE_COLUMN, bitMapToBase64(entity.getImage()));
        cv.put(DESC_COLUMN, entity.getDesc());
        cv.put(ORIGINAL_COLUMN, entity.getOriginalId());

        return cv;
    }
}
