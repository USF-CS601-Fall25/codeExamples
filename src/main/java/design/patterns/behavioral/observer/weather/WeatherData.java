package design.patterns.behavioral.observer.weather;

import java.util.ArrayList;
import java.util.List;

/** Observer Pattern Example. From Head First Design Patterns book.
 *  This class is the "subject", "Observable" that contains data (measured using sensors). When something changes, it
 *  will notify its "Observers". */
public class WeatherData implements Subject {
	private float temperature;
	private float humidity;
	private float pressure;
	private List<Observer> observers;

	public WeatherData() {
		observers = new ArrayList();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}

	/**
	 * When the weather "model" changes (suppose gets new measurements from sensors),
	 * it notifies the observers
	 * @param temperature new temperature measurement
	 * @param humidity new humidity measurement
	 * @param pressure new pressure measurement
	 */
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		notifyObservers();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
}
