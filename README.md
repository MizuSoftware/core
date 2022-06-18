# mizu-core
The core of [Mizu Client][mizu-website], organized into submodules.
Version: `0.0.1`

## modules
- [client-api][module-client-api] - The client base of Mizu, what's used to power the entire client without [feature](#) implementations.
- [struct][module-struct] - The structuring classes, used for annotating or descripting components.

## libraries
In addition to the modules, we also use libraries
- [keen][keen] - The event bus used by Mizu, designed by us.

<!--- Links --->

[mizu-website]: https://mizu.wtf/ "mizu website"

[module-struct]: https://github.com/MizuWtf/core/tree/main/struct "struct module"
[module-client-api]: https://github.com/MizuWtf/core/tree/main/client-api "api module"

[keen]: https://github.com/MizuWtf/keen "keen"