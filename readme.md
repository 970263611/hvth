### HVTH

```yaml
hvth:
  rules: # 无论是对象还是单一属性，都会逐次匹配正则，如果匹配则会使用type的脱敏规则进行脱敏
    - regex: '\d+'
      type: MOBILE_PHONE # 需要和SensitiveType中的某一个规则一致
    - regex: '[\u4e00-\u9fa5]+'
      type: ADDRESS # 需要和SensitiveType中的某一个规则一致
  excludeClass: # 执行排除的类后，当类序列化后的字符串匹配正则规则时也不会被正则规则影响
    - world.dahua.hvth.entity.DemoRequest
```