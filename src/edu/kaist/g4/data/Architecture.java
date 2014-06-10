package edu.kaist.g4.data;

import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 * 
 */

public class Architecture implements IArchitecture {

    protected String archname, id;

    protected Vector<ArchitectureModel>[] viewlist;
    protected Vector<TraceabilityLink> tLinks;

    // protected HashMap<String, TraceabilityLink> tLinks;

    // 객체생성을 언제, 어떻게
    public Architecture(String archname) {
        archname = "NO NAME";
        id = "0000000000";
        viewlist = new Vector[ViewType.values().length];
        for (int i = 0; i < viewlist.length; i++) {
            viewlist[i] = new Vector<ArchitectureModel>();
        }
        // tLinks = new HashMap<String, TraceabilityLink>();
        tLinks = new Vector<TraceabilityLink>();

        id = archname + " " + System.currentTimeMillis();
        this.archname = archname;
    }

    public Architecture(Architecture arch){
        archname = new String(arch.getArchname());
        id = new String(arch.getId());

        viewlist = new Vector[ViewType.values().length];
        for (int i = 0; i < viewlist.length; i++) {
            viewlist[i] = new Vector<ArchitectureModel>();
            Vector<ArchitectureModel>[] list = arch.getViewlist();
            for(ArchitectureModel model : list[i]){
                ArchitectureModel modelClone = new ArchitectureModel(model);              
                viewlist[i].add(modelClone);
            }
        }
        Vector<TraceabilityLink> links = arch.gettLinks();
        for(TraceabilityLink link : links){
            TraceabilityLink linkClone = new TraceabilityLink(link);
            tLinks.add(linkClone);
        }
        
    }
    @Override
    public void addArchitectureModel(ViewType type, ArchitectureModel model) {

        viewlist[type.ordinal()].add(model);
    }

    @Override
    public void addArchitectureElement(ViewType type, String name,
            ArchitectureElement ae) {

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
        ArchitectureModel el;
        while (it.hasNext()) {
            el = it.next();
            if (el.getName().equals(name))
                el.addArchitectureElement(ae);
        }
    }

    @Override
    public void addRelation(ViewType type, String name, Relation r) {

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
        ArchitectureModel el;
        while (it.hasNext()) {
            el = it.next();
            if (el.getName().equals(name))
                el.addRelation(r);
        }

    }

    @Override
    public String overallInformation() {
        // TODO Auto-generated method stub
        String result = "Architecture Information\n\n";
        result += "Name: " + this.archname + "\n";
        result += "ID: " + this.id  + "\n";
        result += "ViewPoint : ";
        for(int i=0;i<this.viewlist.length;i++){
            if(viewlist[i] != null){
                result += ViewType.values()[i].toString();
                if(i != this.viewlist.length-1)
                    result += ", ";
            }
        }
        result += "\n\n";
        
        for(int i=0;i<this.viewlist.length;i++){
            if(viewlist[i] != null){
                result += ViewType.values()[i].toString() + " Views Information\n\n";
                Iterator<ArchitectureModel>it = viewlist[i].iterator();
                while(it.hasNext()){
                    ArchitectureModel model = it.next();
                    result += model.overallInformation();
                    result += "\n";
                }
                result += "\n\n";
            }
        }
        
        result += "TraceabilityLink List\n";
        Iterator<TraceabilityLink> it = this.tLinks.iterator();
        while(it.hasNext()){
            result += it.next().overallInformation() + "\n";
        }
        return result;
    }

    @Override
    public ArchitectureModel getView(ViewType type, String ID) {

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
        ArchitectureModel el;
        while (it.hasNext()) {
            el = it.next();
            if (el.getId().equals(ID))
                return el;
        }
        return null;
    }

    @Override
    public Vector<ArchitectureModel> getArchitectureModels() {
        Vector<ArchitectureModel> result = new Vector<ArchitectureModel>();
        for (int i = 0; i < viewlist.length; i++) {
            Iterator<ArchitectureModel> it = viewlist[i].iterator();
            while (it.hasNext()) {
                result.add(it.next());
            }
        }
        return result;
    }

    @Override
    public boolean addTracebilityLink(String sourceId, Vector<String> destId) {

        // find source and destID reference
        ArchitectureElement sourceRefer = null;
        ArchitectureElement destRefer = null;
        Vector<ArchitectureElement> destlist = new Vector<ArchitectureElement>();

        for (int i = 0; i < viewlist.length; i++) {
            Iterator<ArchitectureModel> it = viewlist[i].iterator();
            while (it.hasNext()) {
                ArchitectureModel model = it.next();
                sourceRefer = model.getElements().get(sourceId);
                if (sourceRefer == null) {
                    Iterator<String> it2 = destId.iterator();
                    while (it2.hasNext()) {
                        destRefer = model.getElements().get(it2.next());
                        if (destRefer != null)
                            destlist.add(destRefer);
                    }
                }
            }
        }

        if (sourceRefer != null && destlist.size() != 0) {
            tLinks.add(new TraceabilityLink(sourceRefer, destlist));
            return true;
        } else
            return false;

    }

    @Override
    public boolean addTracebilityLink(String sourceId, Vector<String> destId,
            ArchitectureModel sorcemodel, ArchitectureModel destmodel) {
        // find source and destID reference
        ArchitectureElement sourceRefer = null;
        ArchitectureElement destRefer = null;
        Vector<ArchitectureElement> destlist = new Vector<ArchitectureElement>();

        for (int i = 0; i < viewlist.length; i++) {
            Iterator<ArchitectureModel> it = viewlist[i].iterator();
            while (it.hasNext()) {
                ArchitectureModel model = it.next();
                sourceRefer = model.getElements().get(sourceId);
                if (sourceRefer == null) {
                    Iterator<String> it2 = destId.iterator();
                    while (it2.hasNext()) {
                        destRefer = model.getElements().get(it2.next());
                        if (destRefer != null)
                            destlist.add(destRefer);
                    }
                }
            }
        }

        if (sourceRefer != null && destlist.size() != 0) {
            tLinks.add(new TraceabilityLink(sorcemodel, sourceRefer, destmodel, destlist));
            return true;
        } else
            return false;
        
        
    }

    public String getArchname() {
        return archname;
    }

    public void setArchname(String archname) {
        this.archname = archname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector<ArchitectureModel>[] getViewlist() {
        return viewlist;
    }

    public void setViewlist(Vector<ArchitectureModel>[] viewlist) {
        this.viewlist = viewlist;
    }

    public Vector<TraceabilityLink> gettLinks() {
        return tLinks;
    }

    public void settLinks(Vector<TraceabilityLink> tLinks) {
        this.tLinks = tLinks;
    }

    @Override
    public ArchitectureModel getArchitectureModelById(String ID) {
        // TODO Auto-generated method stub
        for(int i=0;i<this.viewlist.length;i++){
            Vector<ArchitectureModel> list = viewlist[i];
            Iterator<ArchitectureModel> it = list.iterator();
            while(it.hasNext()){
                ArchitectureModel m = it.next();
                if(m.getId().equals(ID))
                    return m;
            }
        }
        return null;
    }



}
