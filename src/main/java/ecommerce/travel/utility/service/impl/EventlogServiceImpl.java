package ecommerce.travel.utility.service.impl;

import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.mapper.EventlogMapper;
import ecommerce.travel.utility.service.EventlogService;
import org.springframework.stereotype.Service;

@Service
public class EventlogServiceImpl implements EventlogService {

    private EventlogMapper eventlogMapper;

    public EventlogServiceImpl(EventlogMapper eventlogMapper){
        this.eventlogMapper = eventlogMapper;
    }

    @Override
    public Integer updateEventLog(Eventlog eventlog) throws Exception {
        try {
            return eventlogMapper.createEventLog(eventlog);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
