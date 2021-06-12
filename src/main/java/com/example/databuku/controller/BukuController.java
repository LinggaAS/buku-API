package com.example.databuku.controller;

import com.example.databuku.exception.ResourceNotFoundException;
import com.example.databuku.model.Buku;
import com.example.databuku.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BukuController {
    @Autowired
    BukuRepository bukuRepository;

//    Get all buku
    @GetMapping("/buku")
    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

//    Create new buku
    @PostMapping("/buku")
    public Buku createBuku(@Valid @RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

//    Get a single buku
    @GetMapping("/buku/{id}")
    public Buku getBukuById(@PathVariable(value = "id") Long bukuId){
        return bukuRepository.findById(bukuId)
                .orElseThrow(() -> new ResourceNotFoundException("Buku", "Id", bukuId));
    }

//    update buku
    @PutMapping("/buku/{id}")
    public Buku updateBuku(@PathVariable(value = "id") Long bukuId,
                           @Valid @RequestBody Buku bukuDetails){
        Buku buku = bukuRepository.findById(bukuId)
                .orElseThrow(() -> new ResourceNotFoundException("Buku", "id", bukuId));

        buku.setJudul(bukuDetails.getJudul());
        buku.setPengarang(bukuDetails.getPengarang());
        buku.setPenerbit(bukuDetails.getPenerbit());
        buku.setTahunTerbit(bukuDetails.getTahunTerbit());
        buku.setTebal(bukuDetails.getTebal());
        buku.setHarga(bukuDetails.getHarga());

        Buku updatedBuku = bukuRepository.save(buku);
        return updatedBuku;
    }
//    Delete buku
    @DeleteMapping("/buku/{id}")
    public ResponseEntity<?> deleteBuku(@PathVariable(value = "id") Long bukuId) {
        Buku buku = bukuRepository.findById(bukuId)
                .orElseThrow(() -> new ResourceNotFoundException("Buku", "id", bukuId));

        bukuRepository.delete(buku);

        return ResponseEntity.ok().build();
    }
}
