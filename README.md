## DSL realization of 2 endpoints solution with 2 channels. 

There is 2 endpoints in spring integration dsl solution. First read XML files from source (via Pollable Channel) and sending to transform channel. 
Another one endpoit is subcribed to previous channel6 getting Message from it, transforming to JSON String and writting on disk as a File when it is recieved via PubSub Channel.
