package sg.edu.nus.iss.paf_day27.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day27.model.TvShow;
import sg.edu.nus.iss.paf_day27.repository.ShowRepository;

@Service
public class ShowService {
    
    @Autowired
    private ShowRepository showRepository;

    // converting results into Tvshow model
    public List<TvShow> getShowsByType(String type, int limit, int skip){
        return showRepository.getShowsByType(type, limit, skip).stream()
            .map(d -> {
                    return new TvShow(
                        d.getInteger("id"),
                        d.getString("name"),
                        d.getString("summary"),
                        d.get("image", Document.class).getString("original")
                    );
            })
            .toList();
    }
}
