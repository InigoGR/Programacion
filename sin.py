import numpy as np
from scipy.optimize import leastsq
import pylab as plt


class ajuste():

	def __init__(self):
		
		N = 1000 # number of data points
		self.t = np.linspace(0, 4*np.pi, N)
		self.data = 3.0*np.sin(self.t+0.001) + 0.5 + np.random.randn(N) # create artificial data with noise

	def main(self, ecuacion, valores):

		optimize_func = lambda x: x[0]*np.sin(t+x[1]) + x[2] - self.data
		
		y = leastsq(optimize_func, valores)[0] #est_std, est_phase, est_mean = leastsq(optimize_func, [guess_std, guess_phase, guess_mean])[0]








guess_mean = np.mean(data)
guess_std = 3*np.std(data)/(2**0.5)
guess_phase = 0

# we'll use this to plot our first estimate. This might already be good enough for you
data_first_guess = guess_std*np.sin(t+guess_phase) + guess_mean

# Define the function to optimize, in this case, we want to minimize the difference
# between the actual data and our "guessed" parameters

# recreate the fitted curve using the optimized parameters
data_fit = y[0]*np.sin(t+y[1]) + y[2]

plt.plot(data, '.')
plt.plot(data_fit, label='after fitting')
plt.plot(data_first_guess, label='first guess')
plt.legend()
plt.show()