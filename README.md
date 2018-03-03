# orient.supplier.1
IOPS performance test Orient DB

Here is the result of performance on localhost using two types of connections to database (plocal and remote):

OrientDB version | plocal read [IOPS] | plocal write [IOPS] | remote read [IOPS] | remote write [IOPS]
---------------- | ------------------ | ------------------- | ------------------ | --------------------- 	
2.1.4            | 548171             | 974                 | 121129             | 724
2.2.27	         | 380347	          | 2564                | 66484	             | 1195
3.0M2            | 497736	          | 4667                | 42272	             | 1819
3.0RC2           | 601552             | 4473                | 69836              | 2167


Machine specification:
Windows 10, CPU: AMD FX-6300 6 cores 3.5GHz, RAM 8 GB
