package tajo.benchmark

import java.lang.Class
import org.apache.hadoop.conf.Configuration
import nta.conf.NtaConf
;


object Driver {
  def printUsage() {
    println("benchmark BenchmarkClass datadir query")
  }

  def main(args : Array[String]) {
    if (args.length < 3) {
      printUsage();
      System.exit(-1);
    }
    val conf : Configuration = NtaConf.create()
    val clazz = Class.forName(args(0))
    val params = Array[AnyRef](conf, args(1))
    val benchmark = clazz.newInstance().asInstanceOf[BenchmarkSet]

    benchmark.init(conf, args(1))
    benchmark.loadSchemas()
    benchmark.loadQueries()
    benchmark.loadTables()
    benchmark.perform(args(2))
    System.exit(0);
  }
}