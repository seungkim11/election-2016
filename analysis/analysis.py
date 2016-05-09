import numpy
import os
import matplotlib.pyplot as plt
import csv
from pandas import DataFrame, read_csv
import pandas as pd

file = open('../mungee/output.csv', 'rb')

df = pd.read_csv(file)

print df.head()



