/*
 * Copyright 2002-2010 the original author or authors.
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

package org.springframework.integration.ftp;

import org.apache.commons.net.ftp.FTPFile;

import org.springframework.integration.file.AbstractInboundRemoteFileSystemSynchronizingMessageSource;

/**
 * A {@link org.springframework.integration.core.MessageSource} implementation for FTP.
 *
 * @author Iwein Fuld
 * @author Josh Long
 */
public class FtpInboundRemoteFileSystemSynchronizingMessageSource
		extends AbstractInboundRemoteFileSystemSynchronizingMessageSource<FTPFile, FtpInboundRemoteFileSystemSynchronizer> {

	private volatile FtpClientPool clientPool;


	public void setClientPool(FtpClientPool clientPool) {
		this.clientPool = clientPool;
	}

	public String getComponentType() {
		return "ftp:inbound-channel-adapter";
	}

	@Override
	protected void onInit() {
		super.onInit();
		this.synchronizer.setClientPool(this.clientPool);
	}

	@Override
	protected void doStart() {
		this.synchronizer.start();
	}

	@Override
	protected void doStop() {
		this.synchronizer.stop();
	}

}