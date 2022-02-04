package com.lgcns.icst.springthymeleaf.lec5;

import com.lgcns.icst.springthymeleaf.lec4.biz.FreeBoardBiz;
import com.lgcns.icst.springthymeleaf.lec4.dto.FreeBoardDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lec5/free-boards")
public class FreeBoardApi {

    private final FreeBoardBiz freeBoardBiz;

    public FreeBoardApi(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @GetMapping
    public List<FreeBoardDTO> findFreeBoards(@RequestParam(name = "writer_id", required = false) String writerId)
            throws Exception {

        List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();
        if (writerId != null) {
            List<FreeBoardDTO> filtered = new ArrayList<>();
            for (FreeBoardDTO freeBoard : freeBoards) {
                if (freeBoard.getWriterId().equals(writerId)) {
                    filtered.add(freeBoard);
                }
            }
            return filtered;

            // JAVA 8 STREAM 문법
//            return freeBoards.stream()
//                    .filter(freeBoard -> freeBoard.getWriterId().equals(writerId))
//                    .collect(Collectors.toList());
        }
        return freeBoards;
    }
}
