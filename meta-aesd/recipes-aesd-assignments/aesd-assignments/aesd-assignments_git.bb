# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-diferrans1;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "ca86118948ce680b77dad1e118ec4783f78a2041"

# Build from the server subdirectory of the assignments repo
S = "${WORKDIR}/git/server"

EXTRA_OEMAKE += "CROSS_COMPILE=${TARGET_PREFIX}"
TARGET_LDFLAGS += "-pthread -lrt"

do_configure () {
	:
}

do_compile () {
	oe_runmake CC="${CC}" LD="${LD}" CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS} ${TARGET_LDFLAGS}"
}

do_install () {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc6.d
	install -d ${D}${sysconfdir}/rcS.d

	install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket-start-stop
	ln -rs ${D}${sysconfdir}/init.d/aesdsocket-start-stop ${D}${sysconfdir}/rcS.d/S99aesdsocket.sh
	ln -rs ${D}${sysconfdir}/init.d/aesdsocket-start-stop ${D}${sysconfdir}/rc0.d/K99aesdsocket.sh
	ln -rs ${D}${sysconfdir}/init.d/aesdsocket-start-stop ${D}${sysconfdir}/rc6.d/K99aesdsocket.sh
}

FILES:${PN} += "${bindir}/aesdsocket \
                ${sysconfdir}/init.d/aesdsocket-start-stop \
                ${sysconfdir}/rcS.d/S99aesdsocket.sh \
                ${sysconfdir}/rc0.d/K99aesdsocket.sh \
                ${sysconfdir}/rc6.d/K99aesdsocket.sh"
