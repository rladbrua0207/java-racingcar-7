package racingcar.model;

import java.util.List;
import racingcar.utils.RandomNumberGenerator;

public class CarGroup {

    private static final String ERROR_MIN_CARS_MESSAGE = "자동차 이름은 적어도 두 개 입력해야 합니다.";
    private static final int MIN_CARS_SIZE = 2;

    private final List<Car> cars;

    public CarGroup(List<String> carNames) {
        this.cars = initCars(carNames);
        validateSize();
    }

    private List<Car> initCars(List<String> carNames) {
        return carNames.stream().map(Car::new).toList();
    }

    private void validateSize() {
        if (this.cars.size() < MIN_CARS_SIZE) {
            throw new IllegalArgumentException(ERROR_MIN_CARS_MESSAGE);
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getWinners() {
        int maxPosition = getMaxPosition();

        return cars.stream().filter(car -> car.getPosition().equals(maxPosition)).toList();
    }

    private int getMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            if (maxPosition < car.getPosition()) {
                maxPosition = car.getPosition();
            }
        }
        return maxPosition;
    }

    public void move() {
        cars.forEach(car -> {
            car.moveFront(RandomNumberGenerator.generate());
        });
    }
}
