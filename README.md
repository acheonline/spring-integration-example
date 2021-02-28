## DSL realization of 2 endpoints solution with 2 channels. 

There is 2 endpoints in spring integration dsl solution. First is reading files from source and sending to first channel via message. 
Second endpoit is subcribing to another channel and writting file on disk when it is recieved via second channel.

Also there is a bridge between 2 channels that republishs immediately a message from first channel to second.
