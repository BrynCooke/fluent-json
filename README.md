fluent-json
===========

A fluent builder for Google Gson

```java
JsonObject jsonObject = 
	JsonBuilderFactory.buildObject()
		.add("Prop1", "1")
		.add("Prop2", 2)
		.addNull("Prop3")
		.add("Prop4", (String)null)
		.addObject("Prop5")
			.add("NP1", 4)
			.end()
		.addArray("Foo")
			.addObject()
				.end()
			.add("AE1")
		.end()
	.getJson();
```

Copyright 2013 Bryn Cooke
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
        http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 
