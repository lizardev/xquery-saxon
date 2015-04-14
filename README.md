# XQuery Coverage for Saxon implementation

## Continuous integration
[![Build Status](https://travis-ci.org/lizardev/xquery-saxon.svg?branch=master)](https://travis-ci.org/lizardev/xquery-saxon)

## xquery-saxon-support module features
* many `TraceCodeInjector`s and `TraceListener`s supported in the same time via `TraceExtension` interface
* `TraceExtension`s could be provided via generic Service Provider Interface (SPI). It makes prod code independent on potential `TraceExtension`

## xquery-saxon-coverage module features
* Coverage collection from many module compilations
* Coverage report per line and instruction in HTML format

## Configuration
* add `xquery-saxon-support` to prod classpath and use `SpiTraceExtensionProvider` to obtain `TraceExtension`
* add `xquery-saxon-coverage` to test classpath and enable coverage collection via `-Dxquery.saxon.coverage` Java system property

## IDE integration
Provide Java system properties
* `-Dxquery.saxon.coverage` - enables coverage collection
* `-Dxquery.saxon.coverage.report.printing.on.shutdown` - prints coverage reports on JVM shutdown
