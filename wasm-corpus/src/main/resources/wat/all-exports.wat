(module
  (memory (export "mem") 0)
  (table  (export "tab") 10 funcref)
  (global $glob1 (export "glob1") i32 (i32.const 42))
  (global $glob2 (export "glob2") i64 (i64.const 43))
  (global $glob3 (export "glob3") f32 (f32.const 44))
  (global $glob4 (export "glob4") f64 (f64.const 45))
  (func   (export "get-1") (result i32) (global.get $glob1))
  (func   (export "get-2") (result i64) (global.get $glob2))
  (func   (export "get-3") (result f32) (global.get $glob3))
  (func   (export "get-4") (result f64) (global.get $glob4))
  (func   (export "get-5") (param $x i32) (result i32)
      (i32.add (local.get $x) (global.get $glob1)))
  (func   (export "get-6") (param $x i64) (result i64)
      (i64.add (local.get $x) (global.get $glob2)))
  (func   (export "get-7") (param $x f32) (result f32)
      (f32.add (local.get $x) (global.get $glob3)))
  (func   (export "get-8") (param $x f64) (result f64)
      (f64.add (local.get $x) (global.get $glob4)))
  (func   (export "get-9") (param $x1 i32) (param $x2 i64) (param $x3 f32) (param $x4 f64) (result i32)
      (i32.const 46))
  (func   (export "get-10") (param $x1 i32) (param $x2 i64) (param $x3 f32) (param $x4 f64) (result i32) (result i32)
      (i32.const 47) (i32.const 48))
)
