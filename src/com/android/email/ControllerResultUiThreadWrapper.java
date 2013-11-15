/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.android.email;

import com.android.email.Controller.Result;
import com.android.email.mail.MessagingException;

import android.os.Handler;

/**
 * A {@link Result} that wraps another {@link Result} and makes sure methods gets called back
 * on the UI thread.
 */
public class ControllerResultUiThreadWrapper<T extends Result> implements Result {
    private final Handler mHandler;
    private final T mWrappee;

    public ControllerResultUiThreadWrapper(Handler handler, T wrappee) {
        mHandler = handler;
        mWrappee = wrappee;
    }

    public T getWrappee() {
        return mWrappee;
    }

    public void loadAttachmentCallback(final MessagingException result, final long messageId,
            final long attachmentId, final int progress) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.loadAttachmentCallback(result, messageId, attachmentId, progress);
            }
        });
    }

    public void loadMessageForViewCallback(final MessagingException result,
            final long messageId, final int progress) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.loadMessageForViewCallback(result, messageId, progress);
            }
        });
    }

    public void sendMailCallback(final MessagingException result, final long accountId,
            final long messageId, final int progress) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.sendMailCallback(result, accountId, messageId, progress);
            }
        });
    }

    public void serviceCheckMailCallback(final MessagingException result, final long accountId,
            final long mailboxId, final int progress, final long tag) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.serviceCheckMailCallback(result, accountId, mailboxId, progress, tag);
            }
        });
    }

    public void updateMailboxCallback(final MessagingException result, final long accountId,
            final long mailboxId, final int progress, final int numNewMessages) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.updateMailboxCallback(result, accountId, mailboxId, progress,
                        numNewMessages);
            }
        });
    }

    public void updateMailboxListCallback(final MessagingException result, final long accountId,
            final int progress) {
        mHandler.post(new Runnable() {
            public void run() {
                mWrappee.updateMailboxListCallback(result, accountId, progress);
            }
        });
    }
}
