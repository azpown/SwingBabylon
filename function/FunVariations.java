package function;

import static function.Functions.SIN;
import static function.Functions.X;
import static function.Functions.compose;
import static function.Functions.constant;
import static function.Functions.times;

import curves.Variations;

public class FunVariations extends Variations {
	private Function f;

	public FunVariations(Function f, double xmin, double xmax) {
		this.f = f;
		setXmin(xmin);
		setXmax(xmax);
	}

	protected double fun(double x) {
		return f.value(x);
	}
	
	public static FunVariations getStandardFunVar(){
		Function f = 
		times(X, compose(SIN, times(constant(2.), X)));
		return new FunVariations(f, -5, 10);
	}
}
