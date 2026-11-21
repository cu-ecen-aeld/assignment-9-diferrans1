LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "file://hello.c \
           file://faulty.c \
           file://scull.c \
           file://Makefile \
           file://lkm-load.sh"

S = "${UNPACKDIR}"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}"'

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -m 0755 ${UNPACKDIR}/lkm-load.sh ${D}${sysconfdir}/init.d/lkm
    ln -rs ${D}${sysconfdir}/init.d/lkm ${D}${sysconfdir}/rcS.d/S85lkm.sh
}

FILES:${PN} += "${sysconfdir}/init.d/lkm \
                ${sysconfdir}/rcS.d/S85lkm.sh"
