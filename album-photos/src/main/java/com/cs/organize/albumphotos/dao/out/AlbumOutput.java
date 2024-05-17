/**
 * @author KK
 * @version 1.0
 */

package com.cs.organize.albumphotos.dao.out;

import java.util.List;

public record AlbumOutput(long id, String title, List<PhotoOutput> photoOutputList) {
}
