# jmh_microbenchmark

Latest results, made on machine with Intel i5 4-core CPU, 16Gb RAM and SSD.

```
Benchmark                                     Mode  Cnt        Score       Error  Units
JsonBenchmark.jacksonTestBytes               thrpt   20  1013004.163 ±  5734.395  ops/s
JsonBenchmark.jacksonTestBytes_Afterburner   thrpt   20  1053595.535 ±  5925.128  ops/s
JsonBenchmark.jacksonTestString              thrpt   20  1216248.436 ± 18089.361  ops/s
JsonBenchmark.jacksonTestString_Afterburner  thrpt   20  1265308.372 ± 31486.928  ops/s
JsonBenchmark.jsoniterTestBytes              thrpt   20  1189594.800 ± 37092.271  ops/s
JsonBenchmark.jsoniterTestString             thrpt   20  1204493.338 ± 35417.857  ops/s
```
