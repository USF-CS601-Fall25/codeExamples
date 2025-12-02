package generics;

public class MathBoxExample {
	public static void main(String[] args) {
		MathBox<Integer> iBox = new MathBox<>(5);
		Integer v = iBox.getData();
		iBox.setData(17);
		System.out.println(v);

		MathBox<Float> fBox = new MathBox<>(5.0f);


	}
}
