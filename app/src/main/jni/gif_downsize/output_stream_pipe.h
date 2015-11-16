/*
 * Copyright 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//
// Created by Hippo on 10/19/2015.
//

#ifndef OUTPUT_STREAM_PIPE
#define OUTPUT_STREAM_PIPE

#include <jni.h>

#include "output_stream.h"

typedef struct
{
  jobject osPipe;
  jmethodID obtainMID;
  jmethodID releaseMID;
  jmethodID openMID;
  jmethodID closeMID;
} OutputStreamPipe;

OutputStreamPipe* createOutputStreamPipe(JNIEnv* env, jobject osPipe);
void destroyOutputStreamPipe(JNIEnv* env, OutputStreamPipe* outputStreamPipe);
void obtainOutputStreamPipe(JNIEnv* env, OutputStreamPipe* outputStreamPipe);
void releaseOutputStreamPipe(JNIEnv* env, OutputStreamPipe* outputStreamPipe);
OutputStream* openOutputStream(JNIEnv* env, OutputStreamPipe* outputStreamPipe);
void closeOutputStream(JNIEnv* env, OutputStreamPipe* outputStreamPipe);

#endif //OUTPUT_STREAM_PIPE
