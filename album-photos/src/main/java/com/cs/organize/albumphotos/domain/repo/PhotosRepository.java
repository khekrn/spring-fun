/**
 * @author KK
 * @version 1.0
 */

package com.cs.organize.albumphotos.domain.repo;

import com.cs.organize.albumphotos.domain.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<PhotoEntity, Long> {
}
