fluent-json
===========

A fluent builder for Google Gson

```java
JsonObject jsonObject = 
	JsonBuilderFactory.buildObject()
		.add("prop1", "1")
		.add("prop2", 2)
		.addNull("prop3")
		.add("prop4", (String)null)
		.addObject("5rop5")
			.add("np1", 4)
			.end()
		.addArray("prop6")
			.addObject()
				.end()
			.add("ae1")
		.end()
	.getJson();
```
```js
{
	prop1: "1",
	prop2: 2,
	prop3: null,
	prop4: null,
	prop5: {
			np1: 4
		},
	prop6: [
			{},
			"ae1"
		]
}
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
 
