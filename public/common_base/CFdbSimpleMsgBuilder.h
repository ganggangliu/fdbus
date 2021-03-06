/*
 * Copyright (C) 2015   Jeremy Chen jeremy_cz@yahoo.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef __IFDBSIMPLEMSGBUILDER_H__ 
#define __IFDBSIMPLEMSGBUILDER_H__

#include "IFdbMsgBuilder.h"
#include "CFdbSimpleSerializer.h"

template <typename T>
class CFdbSimpleMsgBuilder : public IFdbMsgBuilder
{
public:
    CFdbSimpleMsgBuilder(T message)
        : mMessage(message)
    {}
    int32_t build()
    {
        mSerializer << mMessage;
        return mSerializer.bufferSize();
    }
    void toBuffer(uint8_t *buffer, int32_t size)
    {
        mSerializer.toBuffer(buffer, size);
    }
    CFdbSimpleSerializer &serializer()
    {
        return mSerializer;
    }
    
protected:
    CFdbSimpleSerializer mSerializer;
 
private:
    T mMessage;
};

typedef CFdbSimpleMsgBuilder<const IFdbParcelable &> CFdbParcelableBuilder;

template <typename T>
class CFdbSimpleMsgParser : public IFdbMsgParser
{
public:
    CFdbSimpleMsgParser(T message)
        : mMessage(message)
    {}
    bool parse(const uint8_t *buffer, int32_t size)
    {
        mDeserializer.reset(buffer, size);
        mDeserializer >> mMessage;
        return !mDeserializer.error();
    }
    CFdbSimpleDeserializer &deserializer()
    {
        return mDeserializer;
    };

protected:
    CFdbSimpleDeserializer mDeserializer;
 
private:
    T mMessage;
};

typedef CFdbSimpleMsgParser<IFdbParcelable &> CFdbParcelableParser;

#endif
