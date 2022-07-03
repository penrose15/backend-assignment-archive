package exalted.posset.coffee;

import exalted.posset.coffee.dto.CoffeePatchDto;
import exalted.posset.coffee.dto.CoffeePostDto;
import exalted.posset.coffee.dto.CoffeeResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-04T00:06:55+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CoffeeMapperImpl implements CoffeeMapper {

    @Override
    public Coffee coffeePostToCoffee(CoffeePostDto coffeePostDto) {
        if ( coffeePostDto == null ) {
            return null;
        }

        Coffee coffee = new Coffee();

        coffee.setKorName( coffeePostDto.getKorName() );
        coffee.setEngName( coffeePostDto.getEngName() );
        coffee.setPrice( coffeePostDto.getPrice() );
        coffee.setCoffeeCode( coffeePostDto.getCoffeeCode() );

        return coffee;
    }

    @Override
    public Coffee coffeePatchToCoffee(CoffeePatchDto coffeePatchDto) {
        if ( coffeePatchDto == null ) {
            return null;
        }

        Coffee coffee = new Coffee();

        coffee.setCoffeeId( coffeePatchDto.getCoffeeId() );
        coffee.setKorName( coffeePatchDto.getKorName() );
        coffee.setEngName( coffeePatchDto.getEngName() );
        coffee.setPrice( coffeePatchDto.getPrice() );

        return coffee;
    }

    @Override
    public CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee) {
        if ( coffee == null ) {
            return null;
        }

        CoffeeResponseDto.CoffeeResponseDtoBuilder coffeeResponseDto = CoffeeResponseDto.builder();

        coffeeResponseDto.coffeeId( coffee.getCoffeeId() );
        coffeeResponseDto.korName( coffee.getKorName() );
        coffeeResponseDto.engName( coffee.getEngName() );
        coffeeResponseDto.price( coffee.getPrice() );

        return coffeeResponseDto.build();
    }

    @Override
    public List<CoffeeResponseDto> coffeeToCoffeeResponseDtos(List<Coffee> coffees) {
        if ( coffees == null ) {
            return null;
        }

        List<CoffeeResponseDto> list = new ArrayList<CoffeeResponseDto>( coffees.size() );
        for ( Coffee coffee : coffees ) {
            list.add( coffeeToCoffeeResponseDto( coffee ) );
        }

        return list;
    }
}
