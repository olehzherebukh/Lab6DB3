package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.StateEntity;
import ua.lviv.iot.exceptions.NoSuchStateException;

public class StateDTO extends ResourceSupport {
    StateEntity state;
    public StateDTO(StateEntity state, Link selfLink) throws NoSuchStateException {
        this.state = state;
        add(selfLink);
    }

    public int getStateId() {
        return state.getStateId();
    }

    public String getNameOfState() {
        return state.getNameOfState();
    }
}
