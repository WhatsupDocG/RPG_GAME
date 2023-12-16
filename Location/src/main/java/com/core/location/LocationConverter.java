package com.core.location;

import com.core.location.web.LocationView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter implements Converter<Location, LocationView> {
    @Override
    public LocationView convert(@NonNull Location location) {
        LocationView view = new LocationView();
        view.setId(location.getId());
        view.setName(location.getName());
        return view;
    }

}
